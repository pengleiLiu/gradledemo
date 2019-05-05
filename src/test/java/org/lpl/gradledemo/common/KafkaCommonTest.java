package org.lpl.gradledemo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.MemberAssignment;
import org.apache.kafka.clients.admin.MemberDescription;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

public class KafkaCommonTest {

  private static List<String> getGroupsForTopic(String brokerServers, String topic)
      throws ExecutionException, InterruptedException, TimeoutException {
    Properties props = new Properties();
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerServers);

    try (AdminClient client = AdminClient.create(props)) {
      List<String> allGroups = client.listConsumerGroups()
          .valid()
          .get(10, TimeUnit.SECONDS)
          .stream()
          .map(ConsumerGroupListing::groupId)
          .collect(Collectors.toList());

      Map<String, ConsumerGroupDescription> allGroupDetails =
          client.describeConsumerGroups(allGroups).all().get(10, TimeUnit.SECONDS);

      final List<String> filteredGroups = new ArrayList<>();
      allGroupDetails.entrySet().forEach(entry -> {
        String groupId = entry.getKey();
        ConsumerGroupDescription description = entry.getValue();
        boolean topicSubscribed = description.members().stream().map(MemberDescription::assignment)
            .map(MemberAssignment::topicPartitions)
            .map(tps -> tps.stream().map(TopicPartition::topic).collect(Collectors.toSet()))
            .anyMatch(tps -> tps.contains(topic));
        if (topicSubscribed) {
          filteredGroups.add(groupId);
        }
      });
      return filteredGroups;
    }
  }

  @Test
  public void getGroup() throws InterruptedException, ExecutionException, TimeoutException {
    String brokerServers = "localhost:9092";
    String topicName = "msg-vote";

    System.out.println(GsonUtils.toJson(getGroupsForTopic(brokerServers, topicName)));
  }
}

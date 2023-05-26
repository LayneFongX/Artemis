package com.farid.artemis.checkpoint;

// public class RedisCheckpointStore implements CheckpointStore {
    // @Resource
    // private Redis
    //
    // @Override
    //
    // public void listOwnership(String fullyQualifiedNamespace, String eventHubName, String consumerGroup,
    //                           Iterable<String> partitionIds) {
    //
    // }
    //
    // @Override
    // public Checkpoint getCheckpoint(String fullyQualifiedNamespace, String eventHubName, String consumerGroup,
    //                                 String partitionId) {
    //
    // }
    //
    // @Override
    // public void updateCheckpoint(String fullyQualifiedNamespace, String eventHubName, String consumerGroup,
    //                              Checkpoint checkpoint) {
    //     // 将 Checkpoint 对象转换为字符串，并将其存储到 Redis 中
    //     String checkpointString = convertCheckpointToString(checkpoint);
    //     jedis.set(getKey(eventHubName, consumerGroup, checkpoint.getPartitionId()), checkpointString);
    // }
    //
    //
    // @Override
    // public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
    //     // 在此方法中，您可以根据需要实现对所有权的管理逻辑
    //     return null;
    // }
    //
    // @Override
    // public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
    //     return null;
    // }
    //
    // @Override
    // public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
    //     String checkpointString = jedis.get(getKey(eventHubName, consumerGroup, partitionId));
    //     if (checkpointString != null) {
    //         // 将检查点信息从存储的字符串转换回 Checkpoint 对象
    //         return convertStringToCheckpoint(checkpointString);
    //     }
    //     return null;
    //
    //     return null;
    // }
    //
    // @Override
    // public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
    //     return null;
    // }
    //
    // private String getKey(String eventHubName, String consumerGroup, String partitionId) {
    //     return eventHubName + ":" + consumerGroup + ":" + partitionId;
    // }
    //
    // private String convertCheckpointToString(Checkpoint checkpoint) {
    //     // 在此方法中，将 Checkpoint 对象转换为字符串表示形式（例如 JSON）
    //     // 例如：return JsonConverter.toJson(checkpoint);
    // }
    //
    // private Checkpoint convertStringToCheckpoint(String checkpointString) {
    //     // 在此方法中，将字符串表示形式转换回 Checkpoint 对象
    //     // 例如：return JsonConverter.fromJson(checkpointString, Checkpoint.class);
    // }
// }
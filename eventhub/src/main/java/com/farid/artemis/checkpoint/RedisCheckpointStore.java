package com.farid.artemis.checkpoint;

// public class RedisCheckpointStore implements CheckpointStore {
//
//     private final RedisCommands<String, String> redisCommands;
//
//     public RedisCheckpointStore(String redisHost, int redisPort) {
//         RedisURI redisUri = RedisURI.Builder.redis(redisHost, redisPort).build();
//         RedisClient redisClient = RedisClient.create(redisUri);
//         StatefulRedisConnection<String, String> connection = redisClient.connect();
//         redisCommands = connection.sync();
//     }
//
//
//     @Override
//     public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
//         // 在此方法中，您可以根据需要实现对所有权的管理逻辑
//         return null;
//     }
//
//     @Override
//     public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
//         return null;
//     }
//
//     public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
//         String checkpointString = redisCommands.get(getKey(eventHubName, consumerGroup, partitionId));
//         if (checkpointString != null) {
//             // 将检查点信息从存储的字符串转换回 Checkpoint 对象
//             return convertStringToCheckpoint(checkpointString);
//         }
//         return null;
//     }
//
//     @Override
//     public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
//         redisCommands.set(getKey(checkpoint.getEventHubName(), checkpoint.getConsumerGroup(),
//                 checkpoint.getPartitionId()), convertCheckpointToString(checkpoint));
//         return null;
//     }
//
//     private String getKey(String eventHubName, String consumerGroup, String partitionId) {
//         return eventHubName + ":" + consumerGroup + ":" + partitionId;
//     }
//
//     private String convertCheckpointToString(Checkpoint checkpoint) {
//         return JSON.toJSONString(checkpoint);
//     }
//
//     private Checkpoint convertStringToCheckpoint(String checkpointString) {
//         return JSON.parseObject(checkpointString, Checkpoint.class);
//     }
//
// }
package com.zonelab.whiteboard.server;

import software.amazon.awssdk.auth.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.profile.ProfilesConfigFile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

public class WhiteboardServer {
    static final Map<String, String> ATTRIBUTES_NAMES = new HashMap<>();
    static final Map<String, AttributeValue> ATTRIBUTES_VALUES = new HashMap<>();
    static {
        ATTRIBUTES_NAMES.put("#K", "key");
    }
    public static void main(String[] args) {
        final DynamoDBClient client = DynamoDBClient.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(ProfileCredentialsProvider.builder()
                        .profileName("adminuser")
                        .build())
                .build();
        ListTablesResponse response = client.listTables(ListTablesRequest.builder()
                .limit(5)
                .build());
        response.tableNames().forEach(System.out::println);

        ATTRIBUTES_VALUES.put(":text", AttributeValue.builder().s("first_record").build());
        final QueryRequest queryRequest = QueryRequest.builder()
                .tableName("whiteboard")
                .expressionAttributeNames(ATTRIBUTES_NAMES)
                .expressionAttributeValues(ATTRIBUTES_VALUES)
                .keyConditionExpression("#K = :text")
                .build();
        final QueryResponse query = client.query(queryRequest);
        query.items().forEach(System.out::println);
    }
}

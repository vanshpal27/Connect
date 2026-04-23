package com.project.connect.connections_service.Repository;

import com.project.connect.connections_service.Entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends Neo4jRepository<Person,Long> {

    Optional<Person> getByName(String name);

    // there is unidirectional connection btw two person
    @Query("MATCH (a:Person)-[:CONNECTED_TO]-(b:Person) " +
            "WHERE a.userId = $userId " +
            "RETURN b")
    List<Person> getFirstDegreeConnection(Long userId);

    @Query("MATCH (a:Person)-[r:REQUESTED_TO]->(b:Person) " +
            "WHERE a.userId = $senderId AND b.userId = $receiverId " +
            "RETURN count(r) > 0")
    Boolean checkRequestExists(Long senderId,Long receiverId);


    @Query("MATCH (a:Person)-[c:CONNECTED_TO]-(b:Person) "+
            "Where a.userId = $senderId AND b.userId = $receiverId "+
            "return count(c) > 0")
    Boolean checkConnectionExists(Long senderId,Long receiverId);

    @Modifying
    @Query("MATCH (a:Person),(b:Person) "+
            "Where a.userId = $senderId AND b.userId = $receiverId "+
            "Create (a)-[:REQUESTED_TO]->(b)")
    void addConnectionRequest(Long senderId,Long receiverId);

    @Modifying
    @Query("MATCH (a:Person)-[r:REQUESTED_TO]->(b:Person) "+
            "WHERE a.userId = $senderId AND b.userId = $receiverId "+
            "DELETE r " +
            "CREATE (a)-[:CONNECTED_TO]->(b)")
    void acceptRequest(Long senderId,Long receiverId);
    @Modifying
    @Query("MATCH (a:Person)-[r:REQUESTED_TO]->(b:Person) " +
            "WHERE a.userId = $senderUserId AND b.userId = $receiverUserId " +
            "DELETE r")
    void rejectRequest(Long senderUserId, Long receiverUserId);
}

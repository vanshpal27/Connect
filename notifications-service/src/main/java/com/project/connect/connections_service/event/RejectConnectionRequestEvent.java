package com.project.connect.connections_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RejectConnectionRequestEvent {
    private Long receiverUserId;
    private Long senderUserId;

}

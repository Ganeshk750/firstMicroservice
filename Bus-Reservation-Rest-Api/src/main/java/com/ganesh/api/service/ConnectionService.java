package com.ganesh.api.service;

import java.util.List;

public interface ConnectionService {
    ConnectionDto addConnection(ConnectionDto connectionDto);
    ConnectionDto updateConnection(ConnectionDto connectionDto);
    List<ConnectionDto> getAllConnections();
    void deleteConnection(Long id);
}

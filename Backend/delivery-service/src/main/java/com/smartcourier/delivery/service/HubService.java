package com.smartcourier.delivery.service;

import com.smartcourier.delivery.dto.HubDTO;
import java.util.List;

public interface HubService {
    HubDTO createHub(HubDTO dto);
    HubDTO updateHub(Long id, HubDTO dto);
    void deleteHub(Long id);
    HubDTO getHub(Long id);
    List<HubDTO> getAllHubs();
}

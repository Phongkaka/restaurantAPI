package com.restaurant.deliveryTrack;

import com.restaurant.common.model.DeliveryTrack;
import com.restaurant.common.repository.DeliveryTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryTrackService {

    @Autowired
    DeliveryTrackRepository deliveryTrackRepository;

    /**
     * Find all deliveryTrack
     *
     * @return
     */
    public List<DeliveryTrack> findAll() {
        return deliveryTrackRepository.findAll();
    }

    /**
     * Find deliveryTrack by id
     *
     * @param id
     * @return
     */
    public Optional<DeliveryTrack> findOne(int id) {
        return deliveryTrackRepository.findById(id);
    }

    /**
     * Save deliveryTrack
     *
     * @param deliveryTrack
     * @return
     */
    public DeliveryTrack save(DeliveryTrack deliveryTrack) {
        return deliveryTrackRepository.save(deliveryTrack);
    }

    /**
     * Delete deliveryTrack
     *
     * @param deliveryTrack
     */
    public void delete(Optional<DeliveryTrack> deliveryTrack) {
        deliveryTrackRepository.deleteById(deliveryTrack.get().getId());
    }
}

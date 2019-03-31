package com.restaurant.deliveryTrack;

import com.restaurant.common.exception.DeliveryTrackNotFoundException;
import com.restaurant.common.model.DeliveryTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DeliveryTrackController {

    @Autowired
    DeliveryTrackService deliveryTrackService;

    /**
     * url
     */
    private static final String FIND_ALL_DELIVERY_TRACK_URL = "/deliveryTracks";
    private static final String CREATE_DELIVERY_TRACK_URL = "/deliveryTracks";
    private static final String FIND_DELIVERY_TRACK_BY_ID_URL = "/deliveryTrack/{id}";
    private static final String UPDATE_DELIVERY_TRACK_BY_ID_URL = "/deliveryTrack/{id}";
    private static final String DELETE_DELIVERY_TRACK_BY_ID_URL = "/deliveryTrack/{id}";

    private static final String MSG_DELIVERY_TRACK_NOT_FOUND = "Not found deliveryTrack by id: ";

    /**
     * Find all deliveryTrack.
     *
     * @return
     */
    @GetMapping(FIND_ALL_DELIVERY_TRACK_URL)
    public List<DeliveryTrack> findAll() {
        return deliveryTrackService.findAll();
    }

    /**
     * Find deliveryTrack by id
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_DELIVERY_TRACK_BY_ID_URL)
    public DeliveryTrack findDeliveryTrackById(@PathVariable(value = "id") int id) {

        Optional<DeliveryTrack> deliveryTrack = deliveryTrackService.findOne(id);
        if (!deliveryTrack.isPresent()) {
            throw new DeliveryTrackNotFoundException(MSG_DELIVERY_TRACK_NOT_FOUND + id);
        }
        return deliveryTrack.get();
    }


    /**
     * Created new deliveryTrack
     *
     * @param deliveryTrack
     * @return
     */
    @PostMapping(CREATE_DELIVERY_TRACK_URL)
    public DeliveryTrack createDeliveryTrack(@Valid @RequestBody DeliveryTrack deliveryTrack) {
        return deliveryTrackService.save(deliveryTrack);
    }

    /**
     * Update deliveryTrack by id
     *
     * @param id
     * @param updateDetail
     * @return
     */
    @PutMapping(UPDATE_DELIVERY_TRACK_BY_ID_URL)
    public DeliveryTrack updateDeliveryTrack(@PathVariable(value = "id") int id,
                           @Valid @RequestBody DeliveryTrack updateDetail) {

        Optional<DeliveryTrack> deliveryTrackOptional = deliveryTrackService.findOne(id);
        if (!deliveryTrackOptional.isPresent()) {
            throw new DeliveryTrackNotFoundException(MSG_DELIVERY_TRACK_NOT_FOUND + id);
        } else {
            DeliveryTrack deliveryTrack = deliveryTrackOptional.get();
            deliveryTrack.setEstimatedTime(updateDetail.getEstimatedTime());
            deliveryTrack.setBillId(updateDetail.getBillId());
            deliveryTrack.setStatus(updateDetail.getStatus());
            DeliveryTrack updateDeliveryTrack = deliveryTrackService.save(deliveryTrack);
            return updateDeliveryTrack;
        }
    }

    /**
     * Delete deliveryTrack by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_DELIVERY_TRACK_BY_ID_URL)
    public DeliveryTrack deleteFood(@PathVariable(value = "id") int id) {
        Optional<DeliveryTrack> deliveryTrack = deliveryTrackService.findOne(id);
        if (!deliveryTrack.isPresent()) {
            throw new DeliveryTrackNotFoundException(MSG_DELIVERY_TRACK_NOT_FOUND + id);
        } else {
            deliveryTrackService.delete(deliveryTrack);
            return deliveryTrack.get();
        }
    }
}

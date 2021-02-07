package com.iyhunko.hotel.specifications;

import com.iyhunko.hotel.enums.RoomStatus;
import com.iyhunko.hotel.models.Room;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Locale;

public class RoomSpecification {
    /**
     * if roomClass == null then specification is ignored
     */
    public static Specification<Room> classEquals(String roomClass) {
        return (root, query, builder) ->
                roomClass == null ?
                        builder.conjunction() :
                        builder.like(root.get("roomClass"), "%" + roomClass + "%");
    }

    /**
     * if status == null then specification is ignored
     */
    public static Specification<Room> statusEquals(String status) {
        return (root, query, builder) ->
                (status == null || !RoomStatus.isInEnum(status)) ?
                        builder.conjunction() :
                        builder.equal(root.get("status"), RoomStatus.valueOf(status.toUpperCase(Locale.ROOT)));
    }

    /**
     * if roomsQuantity == null then specification is ignored
     */
    public static Specification<Room> roomsQuantityEquals(Integer roomsQuantity) {
        return (root, query, builder) ->
                roomsQuantity == null ?
                        builder.conjunction() :
                        builder.equal(root.get("roomsQuantity"), roomsQuantity);
    }

    /**
     * if price == null then specification is ignored
     */
    public static Specification<Room> priceGreaterThan(Integer priceFrom) {
        return (root, query, builder) ->
                priceFrom == null ?
                        builder.conjunction() :
                        builder.greaterThanOrEqualTo(root.get("price"), priceFrom);
    }

    /**
     * if price == null then specification is ignored
     */
    public static Specification<Room> priceLessThan(Integer priceTo) {
        return (root, query, builder) ->
                priceTo == null ?
                        builder.conjunction() :
                        builder.lessThanOrEqualTo(root.get("price"), priceTo);
    }
}

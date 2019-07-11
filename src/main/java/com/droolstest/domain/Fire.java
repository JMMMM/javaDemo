package com.droolstest.domain;

/**
 * com.droolstest
 *
 * @author jimmy
 * @date 2019-07-11
 */
public class Fire {
    private Room room;

    public Fire() {
    }

    public Fire(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

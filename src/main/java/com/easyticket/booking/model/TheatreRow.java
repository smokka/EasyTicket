package com.easyticket.booking.model;

import java.util.List;

/**
 * Model representing a Row in a Theatre
 */
public class TheatreRow {

    private int rowId;
    private List<Integer> sections;

    public TheatreRow(int rowId, List<Integer> sections) {
        this.rowId = rowId;
        this.sections = sections;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public List<Integer> getSections() {
        return sections;
    }

    public void setSections(List<Integer> sections) {
        this.sections = sections;
    }




}

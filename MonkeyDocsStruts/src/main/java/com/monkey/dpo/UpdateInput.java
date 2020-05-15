package com.monkey.dpo;

public class UpdateInput {
    private Boolean type;
    private Integer docId;
    private Integer userId;
    private Integer rowId;
    private Integer prevRowId;
    private Integer nextRowId;
    private Integer fragId;
    private Integer offset;
    private Integer prevFragId;
    private Integer nextFragId;
    private String content;

    public Boolean getType() {
        return type;
    }
    public void setType(Boolean type) {
        this.type = type;
    }
    public Integer getDocId() { return docId; }
    public void setDocId(Integer docId) { this.docId = docId; }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRowId() {
        return rowId;
    }
    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }
    public Integer getPrevRowId() {
        return prevRowId;
    }
    public void setPrevRowId(Integer prevRowId) {
        this.prevRowId = prevRowId;
    }
    public Integer getNextRowId() {
        return nextRowId;
    }
    public void setNextRowId(Integer nextRowId) {
        this.nextRowId = nextRowId;
    }
    public Integer getFragId() {
        return fragId;
    }
    public void setFragId(Integer fragId) {
        this.fragId = fragId;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getPrevFragId() {
        return prevFragId;
    }
    public void setPrevFragId(Integer prevFragId) {
        this.prevFragId = prevFragId;
    }
    public Integer getNextFragId() { return nextFragId; }
    public void setNextFragId(Integer nextFragId) { this.nextFragId = nextFragId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

package com.wmp.common.bean;

public class Page
{
    private int indexPage;//当前页
    private int maxRows;//总记录数
    private int maxPage;//总页数
    private int startRows;//起始行
    private int endRows;//终止行
    private boolean hasNext;
    private boolean hasBefore;

    public int getIndexPage()
    {
        return indexPage;
    }

    public void setIndexPage(int indexPage)
    {
        this.indexPage = indexPage;
    }

    public int getMaxRows()
    {
        return maxRows;
    }

    public void setMaxRows(int maxRows)
    {
        this.maxRows = maxRows;
    }

    public int getMaxPage()
    {
        return maxPage;
    }

    public void setMaxPage(int maxPage)
    {
        this.maxPage = maxPage;
    }

    public int getStartRows()
    {
        return startRows;
    }

    public void setStartRows(int startRows)
    {
        this.startRows = startRows;
    }

    public int getEndRows()
    {
        return endRows;
    }

    public void setEndRows(int endRows)
    {
        this.endRows = endRows;
    }

    public boolean isHasNext()
    {
        return hasNext;
    }

    public void setHasNext(boolean hasNext)
    {
        this.hasNext = hasNext;
    }

    public boolean isHasBefore()
    {
        return hasBefore;
    }

    public void setHasBefore(boolean hasBefore)
    {
        this.hasBefore = hasBefore;
    }
}

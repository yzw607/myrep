package com.wmp.selfService.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.selfService.bean.Stencil;
import com.wmp.selfService.service.IStencilService;

@SuppressWarnings("serial")
public class StencilAction extends ActionSupport
{
    private IStencilService stencilService;
    private List<Stencil> list;

    public String queryStencil() throws Exception
    {
        return SUCCESS;
    }
    
    public IStencilService getStencilService()
    {
        return stencilService;
    }

    public void setStencilService(IStencilService stencilService)
    {
        this.stencilService = stencilService;
    }

    public List<Stencil> getList()
    {
        return list;
    }

    public void setList(List<Stencil> list)
    {
        this.list = list;
    }
}

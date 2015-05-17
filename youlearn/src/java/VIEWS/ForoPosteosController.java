package VIEWS;

import ENTITIES.ForoPosteos;
import VIEWS.util.JsfUtil;
import VIEWS.util.PaginationHelper;
import MODEL.ForoPosteosFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;


@Named("foroPosteosController")
@SessionScoped
public class ForoPosteosController implements Serializable {

    private ForoPosteos current;
    private DataModel items = null;
    @EJB
    private MODEL.ForoPosteosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<ForoPosteos> carga = new ArrayList();

    public ForoPosteos getCurrent() {
        return current;
    }

    public void setCurrent(ForoPosteos current) {
        this.current = current;
    }

    public ForoPosteosFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ForoPosteosFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public List<ForoPosteos> getCarga() {
        return carga;
    }

    public void setCarga(List<ForoPosteos> carga) {
        this.carga = carga;
    }

    public List<ForoPosteos> getCarga2() {
        return carga2;
    }

    public void setCarga2(List<ForoPosteos> carga2) {
        this.carga2 = carga2;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }
    private List<ForoPosteos> carga2 = new ArrayList();
    private String tit;
    private int idPost;

    public ForoPosteosController() {
    }

    public ForoPosteos getSelected() {
        if (current == null) {
            current = new ForoPosteos();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ForoPosteosFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ForoPosteos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ForoPosteos();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ForoPosteosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ForoPosteos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ForoPosteosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ForoPosteos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ForoPosteosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
    
    
         public List<ForoPosteos> cargaPosts(int idsc)
    {
        if(idsc == 0)
        {
            carga.clear();
            return carga = ejbFacade.findAll();
        }else
        {
            carga.clear();
            carga2.clear();
            carga = ejbFacade.findAll();
            for(int i=0;i<carga.size();i++)
            {
                if(carga.get(i).getIdSubcategoria().getIdSubcategoria() == idsc)
                {
                    carga2.add(carga.get(i));
                }
            }
            return carga2;
        }
    }
         
         //experimental,no testeado
         public List<ForoPosteos> cargaPostsNoticias(int idsc)
    {
        if(idsc == 0)
        {
            carga.clear();
            return carga = ejbFacade.findAll();
        }else
        {
            carga.clear();
            carga2.clear();
            carga = ejbFacade.findAll();//definir query que busque id noticias
            for(int i=0;i<carga.size();i++)
            {
                if(carga.get(i).getIdSubcategoria().getIdSubcategoria() == idsc)
                {
                    carga2.add(carga.get(i));
                }
            }
            return carga2;
        }
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public ForoPosteos getForoPosteos(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ForoPosteos.class)
    public static class ForoPosteosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ForoPosteosController controller = (ForoPosteosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "foroPosteosController");
            return controller.getForoPosteos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ForoPosteos) {
                ForoPosteos o = (ForoPosteos) object;
                return getStringKey(o.getIdPost());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ForoPosteos.class.getName());
            }
        }

    }

}

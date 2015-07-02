package VIEWS;

import ENTITIES.PublicacionPerfil;
import ENTITIES.Usuario;
import VIEWS.util.JsfUtil;
import VIEWS.util.PaginationHelper;
import MODEL.PublicacionPerfilFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@Named("publicacionPerfilController")
@SessionScoped
public class PublicacionPerfilController implements Serializable {

    private PublicacionPerfil current;
    private DataModel items = null;
    @EJB
    private MODEL.PublicacionPerfilFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
        
    private List<PublicacionPerfil> pp1 = new ArrayList();

    public List<PublicacionPerfil> getPp1() {
        return pp1;
    }

    public void setPp1(List<PublicacionPerfil> pp1) {
        this.pp1 = pp1;
    }

    public List<PublicacionPerfil> getPp2() {
        return pp2;
    }

    public void setPp2(List<PublicacionPerfil> pp2) {
        this.pp2 = pp2;
    }
    private List<PublicacionPerfil> pp2 = new ArrayList();

    public PublicacionPerfilController() {
    }

    public PublicacionPerfil getSelected() {
        if (current == null) {
            current = new PublicacionPerfil();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PublicacionPerfilFacade getFacade() {
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
        current = (PublicacionPerfil) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PublicacionPerfil();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PublicacionPerfilCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PublicacionPerfil) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PublicacionPerfilUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PublicacionPerfil) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PublicacionPerfilDeleted"));
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
    
     public String crearPublicacion(int idmuro,int idpublicador)//id muro = id usuario poseedor el muro
    {
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();            
            String fecha = dateFormat.format(date);
            
            Usuario ou = new Usuario();
            Usuario ou2 = new Usuario();
            ou.setIdUsuario(idmuro);
            ou2.setIdUsuario(idpublicador);
                      
            
          
            if(ou.getIdUsuario()==0)
            {
              current.setIdUsuario(ou2);
            current.setIdPublicador(ou2);
            
                
            }
            else{
                
              current.setIdUsuario(ou);
            current.setIdPublicador(ou2);
            
                
            }
            
            
            
           current.setFechaPublicacion(dateFormat.parse(fecha));
           getFacade().create(current); 
           current = null;
           
           return "/perfil.xhtml";
        }catch(Exception e)
        {
            System.out.println("ID DEL USUARIO :"+ current.getIdUsuario());
   
              System.out.println("ID DEL PUBLICADOR :"+ current.getIdPublicador());
            System.out.println("Error Al crear "+e);
            return "/perfil.xhtml";
        }
        
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public PublicacionPerfil getPublicacionPerfil(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public List<PublicacionPerfil> cargaPublicaciones(int idowner)
    {
        pp1.clear();
        pp2.clear();
        pp1 = ejbFacade.findAll();
        
        
        for(int i = 0;i<pp1.size();i++)
        {
            if(pp1.get(i).getIdUsuario().getIdUsuario() == idowner)
            {
               
                    pp2.add(pp1.get(i));
                
            }
        }
        return pp2;
        
    }

    @FacesConverter(forClass = PublicacionPerfil.class)
    public static class PublicacionPerfilControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PublicacionPerfilController controller = (PublicacionPerfilController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "publicacionPerfilController");
            return controller.getPublicacionPerfil(getKey(value));
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
            if (object instanceof PublicacionPerfil) {
                PublicacionPerfil o = (PublicacionPerfil) object;
                return getStringKey(o.getIdPublicacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PublicacionPerfil.class.getName());
            }
        }

    }

}

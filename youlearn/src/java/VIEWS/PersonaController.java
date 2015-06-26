package VIEWS;

import ENTITIES.Persona;
import VIEWS.util.JsfUtil;
import VIEWS.util.PaginationHelper;
import MODEL.PersonaFacade;

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

@Named("personaController")
@SessionScoped
public class PersonaController implements Serializable {

    private Persona current;
    private DataModel items = null;
    @EJB
    private MODEL.PersonaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<Persona> cargaPer = new ArrayList();
     private List<Persona> carga2 = new ArrayList();

    public List<Persona> getCarga2() {
        return carga2;
    }

    public void setCarga2(List<Persona> carga2) {
        this.carga2 = carga2;
    }

    public Persona getCurrent() {
        return current;
    }

    public void setCurrent(Persona current) {
        this.current = current;
    }

    public PersonaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PersonaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public List<Persona> getCargaPer() {
        return cargaPer;
    }

    public void setCargaPer(List<Persona> cargaPer) {
        this.cargaPer = cargaPer;
    }

    public PersonaController() {
    }

    public Persona getSelected() {
        if (current == null) {
            current = new Persona();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PersonaFacade getFacade() {
        return ejbFacade;
    }
    
    public boolean datosVacios(){
         return carga2.isEmpty();
           
      
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
        current = (Persona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Persona();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersonaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Persona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
     public void prepareEditPerfil(int idUsur) {//Carga usuario dependiendo de la id perfil para poder editar
       
        cargaPer.clear();
        cargaPer = ejbFacade.findAll();
        for(int i=0;i<cargaPer.size();i++)
        {
            if(cargaPer.get(i).getIdUsuario() == idUsur)
            {
                current = cargaPer.get(i);
            }
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersonaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Persona) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersonaDeleted"));
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
    
        public List<Persona> cargaDatos2(int id)
     {
         cargaPer.clear();
         carga2.clear();
         cargaPer = ejbFacade.findAll();//Cargo TODOS los DATOS de PERSONA TODOS DOVO
         if(cargaPer != null && carga2 != null)
         {
            for(int i=0;i<cargaPer.size();i++)
            {
                if(cargaPer.get(i).getIdUsuario() == id)
                {
                    carga2.add(cargaPer.get(i));
                }
            }
         }
         return carga2;
     }
        
         private boolean existePersona(int idus)
    {
        cargaPer.clear();
        cargaPer = ejbFacade.findAll();//cargamos todas las personas en cargaPer
        boolean r = false;
        for(int i=0;i<cargaPer.size();i++)
        {
            if(cargaPer.get(i).getIdUsuario() == idus)
            {
                r = true;
            }else
            {
                r = false;
            }
        }
        return r;
        
    }
        

        
         public String createPersonaUsuario(int idu) {
        
        
        if(existePersona(idu)){
            try {
           
                
            current.setIdUsuario(idu);
            getFacade().edit(current);
            
        
        return "/cuenta.xhtml";
            
           // return prepareCreate();
        } catch (Exception e) {
               System.out.println("ERRORS "+e);
            
            return "/cuenta.xhtml";
        }
            
        }
        else{
        try {
           
 
            current.setIdUsuario(idu);
            getFacade().create(current);
            
            return "/cuenta.xhtml";
        } catch (Exception e) {
               System.out.println("ERRORS "+e);
          
            return "/cuenta.xhtml";
        }
    }
        
      
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Persona getPersona(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Persona.class)
    public static class PersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaController controller = (PersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaController");
            return controller.getPersona(getKey(value));
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
            if (object instanceof Persona) {
                Persona o = (Persona) object;
                return getStringKey(o.getIdPersona());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Persona.class.getName());
            }
        }

    }

}

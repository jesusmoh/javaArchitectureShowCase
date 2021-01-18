package redcloud.controllers.ui;

import redcloud.model.persistence.entities.WebMaCiudades;
import redcloud.controllers.ui.util.JsfUtil;
import redcloud.controllers.ui.util.JsfUtil.PersistAction;
import redcloud.model.persistence.dao.WebMaCiudadesFacadeDAO;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("webMaCiudadesController")
@SessionScoped
public class WebMaCiudadesController implements Serializable {

    @EJB
    private redcloud.model.persistence.dao.WebMaCiudadesFacadeDAO ejbFacade;
    private List<WebMaCiudades> items = null;
    private WebMaCiudades selected;

    public WebMaCiudadesController() {
    }

    public WebMaCiudades getSelected() {
        return selected;
    }

    public void setSelected(WebMaCiudades selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WebMaCiudadesFacadeDAO getFacade() {
        return ejbFacade;
    }

    public WebMaCiudades prepareCreate() {
        selected = new WebMaCiudades();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WebMaCiudadesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WebMaCiudadesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WebMaCiudadesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<WebMaCiudades> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public WebMaCiudades getWebMaCiudades(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<WebMaCiudades> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<WebMaCiudades> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = WebMaCiudades.class)
    public static class WebMaCiudadesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WebMaCiudadesController controller = (WebMaCiudadesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "webMaCiudadesController");
            return controller.getWebMaCiudades(getKey(value));
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
            if (object instanceof WebMaCiudades) {
                WebMaCiudades o = (WebMaCiudades) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), WebMaCiudades.class.getName()});
                return null;
            }
        }

    }

}

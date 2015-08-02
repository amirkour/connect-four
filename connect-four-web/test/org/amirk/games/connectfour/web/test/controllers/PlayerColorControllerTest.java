
package org.amirk.games.connectfour.web.test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.amirk.games.connectfour.db.DAOPlayerColor;
import org.amirk.games.connectfour.entities.PlayerColor;
import org.amirk.games.connectfour.web.controllers.PlayerColorController;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

public class PlayerColorControllerTest {
    
    @Test
    public void listActionShouldPopulateModel() throws Exception {
        List<PlayerColor> dummyTypes = this.createDummyPlayerColors();
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        when(mockDao.getList()).thenReturn(dummyTypes);
        
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        
        MockMvc mvc = standaloneSetup(ctrl).setSingleView(new InternalResourceView("/WEB-INF/views/playercolors/list.jsp")).build();
        mvc.perform(get("/playercolors"))
           .andExpect(model().attributeExists("playerColorList"))
           .andExpect(model().attribute("playerColorList", hasItems(dummyTypes.toArray())));
    }
    
    @Test
    public void listActionView() throws Exception {
        List<PlayerColor> dummyColors = this.createDummyPlayerColors();
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        when(mockDao.getList()).thenReturn(dummyColors);
        
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        
        MockMvc mvc = standaloneSetup(ctrl).setSingleView(new InternalResourceView("/WEB-INF/views/playercolors/list.jsp")).build();
        mvc.perform(get("/playercolors"))
           .andExpect(view().name("playercolors/list"));
    }
    
    
    @Test
    public void saveActionShouldFlashErrorAndRedirectIfNameParamMissing() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/save"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
        
        mvc.perform(post("/playercolors/save").param("name"," "))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
    }
    
    @Test
    public void saveActionShouldFlashErrorAndRedirectIfIdPresent() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/save").param("id", "1")
                                             .param("name","foo"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists(("error")));
    }
    
    @Test
    public void saveActionRedirectsToListWithValidParams() throws Exception{
        String newName = "bar";
        int id = 123;
        PlayerColor original = new PlayerColor();
        original.setName(newName);
        
        PlayerColor saved = new PlayerColor();
        saved.setId(id);
        saved.setName(newName);
        
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        when(mockDao.save(original)).thenReturn(saved);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/save").param("name",newName))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("success"));
        
        verify(mockDao, atLeastOnce()).save(original);
    }
    
    @Test
    public void updateWithoutNumericIdFailsOutright() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/update").param("id",""))
           .andExpect(status().isBadRequest());
        
        mvc.perform(post("/playercolors/update").param("id","hi"))
           .andExpect(status().isBadRequest());
    }
    
    @Test
    public void updateWithoutValidIdRedirectsWithErrorFlash() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/update").param("name","foo")
                                               .param("id", "0"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
        
        mvc.perform(post("/playercolors/update").param("name","foo")
                                               .param("id", "-1"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
    }
    
    @Test
    public void updateWithoutNameRedirectsWithErrorFlash() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/update").param("name","")
                                               .param("id", "1"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
        
        mvc.perform(post("/playercolors/update").param("id", "1"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
    }
    
    @Test
    public void updateWithValidParamsRedirectsWithSuccessFlash() throws Exception{
        int id = 1;
        String name = "foo";
        
        PlayerColor colorToUpdate = new PlayerColor();
        colorToUpdate.setId(id);
        colorToUpdate.setName(name);
        
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        when(mockDao.update(colorToUpdate)).thenReturn(colorToUpdate);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/update").param("name",name)
                                               .param("id", String.valueOf(id)))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("success"));
        
        verify(mockDao, atLeastOnce()).update(colorToUpdate);
    }
    
    @Test
    public void deleteWithoutNumericIdFailsOutright() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/delete").param("id",""))
           .andExpect(status().isBadRequest());
        
        mvc.perform(post("/playercolors/delete").param("id","hi"))
           .andExpect(status().isBadRequest());
    }
    
    @Test
    public void deleteWithoutValidIdRedirectsWithErrorFlash() throws Exception{
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/delete").param("name","foo")
                                               .param("id", "0"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
        
        mvc.perform(post("/playercolors/delete").param("name","foo")
                                               .param("id", "-1"))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("error"));
    }
    
    @Test
    public void deleteWithValidParamsRedirectsWithSuccessFlash() throws Exception{
        int id = 1;
        String name = "foo";
        
        PlayerColor colorToDelete = new PlayerColor();
        colorToDelete.setId(id);
        colorToDelete.setName(name);
        
        DAOPlayerColor mockDao = mock(DAOPlayerColor.class);
        when(mockDao.update(colorToDelete)).thenReturn(colorToDelete);
        PlayerColorController ctrl = new PlayerColorController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playercolors/delete").param("name",name)
                                               .param("id", String.valueOf(id)))
           .andExpect(redirectedUrl("/playercolors"))
           .andExpect(flash().attributeExists("success"));
        
        verify(mockDao, atLeastOnce()).delete(colorToDelete);
    }
    
    protected List<PlayerColor> createDummyPlayerColors(){
        List<PlayerColor> colors = new ArrayList<PlayerColor>();
        for(int i = 0; i < 1; i++){
            PlayerColor dummy = new PlayerColor();
            dummy.setId(i);
            dummy.setName("foo" + i);
            colors.add(dummy);
        }
        
        return colors;
    }
}


package org.amirk.games.connectfour.web.test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.amirk.games.connectfour.db.DAOPlayerType;
import org.amirk.games.connectfour.entities.PlayerType;
import org.amirk.games.connectfour.web.controllers.PlayerTypeController;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

public class PlayerTypeControllerTest {
    
    @Test
    public void listActionShouldPopulateModel() throws Exception {
        List<PlayerType> dummyTypes = this.createDummyPlayerTypes();
        DAOPlayerType mockDao = mock(DAOPlayerType.class);
        when(mockDao.getList()).thenReturn(dummyTypes);
        
        PlayerTypeController ctrl = new PlayerTypeController(mockDao);
        
        MockMvc mvc = standaloneSetup(ctrl).setSingleView(new InternalResourceView("/WEB-INF/views/playertypes/list.jsp")).build();
        mvc.perform(get("/playertypes"))
           .andExpect(model().attributeExists("playerTypeList"))
           .andExpect(model().attribute("playerTypeList", hasItems(dummyTypes.toArray())));
    }
    
    @Test
    public void listActionView() throws Exception {
        List<PlayerType> dummyTypes = this.createDummyPlayerTypes();
        DAOPlayerType mockDao = mock(DAOPlayerType.class);
        when(mockDao.getList()).thenReturn(dummyTypes);
        
        PlayerTypeController ctrl = new PlayerTypeController(mockDao);
        
        MockMvc mvc = standaloneSetup(ctrl).setSingleView(new InternalResourceView("/WEB-INF/views/playertypes/list.jsp")).build();
        mvc.perform(get("/playertypes"))
           .andExpect(view().name("playertypes/list"));
    }
    
    @Test
    public void saveActionShouldFlashErrorAndRedirectIfNameParamMissing() throws Exception{
        DAOPlayerType mockDao = mock(DAOPlayerType.class);
        PlayerTypeController ctrl = new PlayerTypeController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playertypes/save"))
           .andExpect(redirectedUrl("/playertypes"))
           .andExpect(flash().attributeExists("error"));
        
        mvc.perform(post("/playertypes/save").param("name"," "))
           .andExpect(redirectedUrl("/playertypes"))
           .andExpect(flash().attributeExists("error"));
    }
    
    @Test
    public void saveActionShouldFlashErrorAndRedirectIfIdPresent() throws Exception{
        DAOPlayerType mockDao = mock(DAOPlayerType.class);
        PlayerTypeController ctrl = new PlayerTypeController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playertypes/save").param("id", "1")
                                             .param("name","foo"))
           .andExpect(redirectedUrl("/playertypes"))
           .andExpect(flash().attributeExists(("error")));
    }
    
    @Test
    public void saveActionRedirectsToListWithValidParams() throws Exception{
        String newName = "bar";
        int id = 123;
        PlayerType original = new PlayerType();
        original.setName(newName);
        
        PlayerType saved = new PlayerType();
        saved.setId(id);
        saved.setName(newName);
        
        DAOPlayerType mockDao = mock(DAOPlayerType.class);
        when(mockDao.save(original)).thenReturn(saved);
        PlayerTypeController ctrl = new PlayerTypeController(mockDao);
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(post("/playertypes/save").param("name",newName))
           .andExpect(redirectedUrl("/playertypes"))
           .andExpect(flash().attributeExists("success"));
        
        verify(mockDao, atLeastOnce()).save(original);
    }
    
    protected List<PlayerType> createDummyPlayerTypes(){
        List<PlayerType> types = new ArrayList<PlayerType>();
        for(int i = 0; i < 1; i++){
            PlayerType dummy = new PlayerType();
            dummy.setId(i);
            dummy.setName("foo" + i);
            types.add(dummy);
        }
        
        return types;
    }
}

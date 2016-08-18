package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.view.util.ActionButton;
import java.util.List;
import javafx.scene.control.Control;

/**
 * @author artur
 * @param <T>
 */
public interface EntrieController <T extends Core>
{
    public List<ActionButton> getActions();
    
    public void refresh();
    
    public Control getComponent();
}

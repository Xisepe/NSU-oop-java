package model.tree;

import lombok.Data;
import model.state.Position;

@Data
public class TreeBlock {
    private boolean chopped;
    private boolean branch;
    private Position branchPosition;
}

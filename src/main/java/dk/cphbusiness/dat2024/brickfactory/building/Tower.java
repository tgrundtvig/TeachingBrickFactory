package dk.cphbusiness.dat2024.brickfactory.building;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;

public class Tower implements ModelDesigner
{
    private final ModelBuildTool buildTool;

    public Tower(ModelBuildTool modelBuildTool)
    {
        this.buildTool = modelBuildTool;
    }
    
    @Override
    public Model DesignModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {
        Brick basic2x2 = brickFactory.createBasicBrick(2, 2, 1);
        buildTool.placeBrick(basic2x2);
        Brick elevatedBasic2x2 = buildTool.translateZ(1, basic2x2);
        buildTool.placeBrick(elevatedBasic2x2);
        return buildTool.getModel();
    }
}
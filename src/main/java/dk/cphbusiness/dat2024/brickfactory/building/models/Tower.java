package dk.cphbusiness.dat2024.brickfactory.building.models;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import dk.cphbusiness.dat2024.brickfactory.building.Model;
import dk.cphbusiness.dat2024.brickfactory.building.ModelBuildTool;
import dk.cphbusiness.dat2024.brickfactory.building.ModelDesign;

public class Tower implements ModelDesign
{
    @Override
    public Model designModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {
        Brick basic2x2 = brickFactory.createBasicBrick(2, 2, 1);
        buildTool.placeBrick(basic2x2);
        Brick elevatedBasic2x2 = buildTool.translateZ(1, basic2x2);
        buildTool.placeBrick(elevatedBasic2x2);
        return buildTool.getModel();
    }
}

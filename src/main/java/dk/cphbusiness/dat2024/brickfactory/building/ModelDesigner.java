package dk.cphbusiness.dat2024.brickfactory.building;

import dk.cphbusiness.dat2024.brickfactory.BrickFactory;

public interface ModelDesigner
{
    Model DesignModel(ModelBuildTool buildTool, BrickFactory brickFactory);
}

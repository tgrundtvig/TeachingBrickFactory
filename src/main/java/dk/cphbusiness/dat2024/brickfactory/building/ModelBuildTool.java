package dk.cphbusiness.dat2024.brickfactory.building;

import dk.cphbusiness.dat2024.brickfactory.Brick;

public interface ModelBuildTool
{
    default Brick translateX(int x, Brick brick)
    {
        return translate(x, 0,0, brick);
    }
    default Brick translateY(int y, Brick brick)
    {
        return translate(0, y,0, brick);
    }
    default Brick translateZ(int z, Brick brick)
    {
        return translate(0, 0,z, brick);
    }
    Brick translate(int x, int y, int z, Brick brick);
    Brick rotate(int clicks, Brick brick);
    void placeBrick(Brick brick);
    Model getModel();
}

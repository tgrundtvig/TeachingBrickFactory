package dk.cphbusiness.dat2024.brickfactory.building.models;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import dk.cphbusiness.dat2024.brickfactory.building.*;
import dk.cphbusiness.dat2024.brickfactory.building.impl.ModelViewerImpl;
import dk.cphbusiness.dat2024.brickfactory.building.impl.MyModelFactoryImpl;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class SmartDesign implements ModelDesign
{
    @Override
    public Model designModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {
        Brick brick_4x2x1 = brickFactory.createBasicBrick(4, 2, 1);
        Brick brick_2x4x1 = brickFactory.createBasicBrick(2, 4, 1);
        makeXWall(buildTool, 3, 6, 4, 1, 0, 0, 0, brick_4x2x1);
        makeYWall(buildTool, 4, 4, 4, 1, 0,0,0, brick_2x4x1);
        return buildTool.getModel();
    }

    private void makeXWall(ModelBuildTool bt,
                           int numberOfBricks,
                           int height,
                           int xDistance,
                           int zDistance,
                           int xOffset, int yOffset, int zOffset,
                           Brick brick)
    {
        int curZ = zOffset;
        for(int z = 0; z < height; ++z)
        {
            int curXOffset = z % 2 == 0 ? xOffset : xOffset + xDistance/2;
            makeXLine(bt, numberOfBricks, xDistance, curXOffset, yOffset, curZ, brick);
            curZ += zDistance;
        }
    }

    private void makeYWall(ModelBuildTool bt,
                           int numberOfBricks,
                           int height,
                           int yDistance,
                           int zDistance,
                           int xOffset, int yOffset, int zOffset,
                           Brick brick)
    {
        int curZ = zOffset;
        for(int z = 0; z < height; ++z)
        {
            int curYOffset = z % 2 == 0 ? yOffset + yDistance/2 : yOffset;
            makeYLine(bt, numberOfBricks, yDistance, xOffset, curYOffset, curZ, brick);
            curZ += zDistance;
        }
    }

    private void makeXLine(
            ModelBuildTool bt,
            int numberOfBricks,
            int distance,
            int xOffset, int yOffset, int zOffset,
            Brick brick)
    {
        int curPosition = xOffset;
        for(int i = 0; i < numberOfBricks; ++i)
        {
            Brick curBrick = bt.translate(curPosition, yOffset, zOffset, brick);
            bt.placeBrick(curBrick);
            curPosition = curPosition + distance;
        }
    }
    private void makeYLine(
            ModelBuildTool bt,
            int numberOfBricks,
            int distance,
            int xOffset, int yOffset, int zOffset,
            Brick brick)
    {
        int curPosition = yOffset;
        for(int i = 0; i < numberOfBricks; ++i)
        {
            Brick curBrick = bt.translate(xOffset, curPosition, zOffset, brick);
            bt.placeBrick(curBrick);
            curPosition = curPosition + distance;
        }
    }



    public static void main(String[] args)
    {
        double unit = 8;
        JavaCSG csg = JavaCSGFactory.createNoCaching();
        ModelFactory modelFactory = new MyModelFactoryImpl(csg, unit);
        Model test = modelFactory.createModel(new SmartDesign());
        ModelViewer viewer = new ModelViewerImpl(csg);
        viewer.view(test);
    }
}

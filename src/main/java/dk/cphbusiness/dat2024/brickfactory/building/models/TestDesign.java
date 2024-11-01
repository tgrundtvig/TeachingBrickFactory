package dk.cphbusiness.dat2024.brickfactory.building.models;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import dk.cphbusiness.dat2024.brickfactory.building.*;
import dk.cphbusiness.dat2024.brickfactory.building.impl.MockupModelFactoryImpl;
import dk.cphbusiness.dat2024.brickfactory.building.impl.ModelViewerImpl;
import dk.cphbusiness.dat2024.brickfactory.building.impl.MyModelFactoryImpl;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class TestDesign implements ModelDesign
{
    @Override
    public Model designModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {
        Brick brick_4x2 = brickFactory.createBasicBrick(4, 2, 1);
        buildTool.placeBrick(brick_4x2);
        brick_4x2 = buildTool.translateX(4, brick_4x2);
        buildTool.placeBrick(brick_4x2);
        brick_4x2 = buildTool.translate(-2, 0, 1, brick_4x2);
        buildTool.placeBrick(brick_4x2);
        brick_4x2 = buildTool.rotate(1, brick_4x2);
        brick_4x2 = buildTool.translate(0,2,-1, brick_4x2);
        buildTool.placeBrick(brick_4x2);
        brick_4x2 = buildTool.translate(0,2,1, brick_4x2);
        buildTool.placeBrick(brick_4x2);
        return buildTool.getModel();
    }

    private void makeXLine(ModelBuildTool bt, int numberOfBricks, int distance, int offset, Brick brick)
    {
        int curPosition = offset;
        for(int i = 0; i < numberOfBricks; ++i)
        {
            Brick curBrick = bt.translateX(curPosition, brick);
            bt.placeBrick(curBrick);
            curPosition = curPosition + distance;
        }
    }



    public static void main(String[] args)
    {
        double unit = 10;
        JavaCSG csg = JavaCSGFactory.createNoCaching();
        ModelFactory modelFactory = new MockupModelFactoryImpl(csg, unit);
        Model test = modelFactory.createModel(new TestDesign());
        ModelViewer viewer = new ModelViewerImpl(csg);
        viewer.view(test);
    }
}

package dk.cphbusiness.dat2024.brickfactory.building.models;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import dk.cphbusiness.dat2024.brickfactory.building.Model;
import dk.cphbusiness.dat2024.brickfactory.building.ModelBuildTool;
import dk.cphbusiness.dat2024.brickfactory.building.ModelDesign;
import dk.cphbusiness.dat2024.brickfactory.building.ModelViewer;
import dk.cphbusiness.dat2024.brickfactory.building.impl.MockupModelFactoryImpl;
import dk.cphbusiness.dat2024.brickfactory.building.impl.ModelViewerImpl;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class TestDesign implements ModelDesign
{
    @Override
    public Model designModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {
        Brick test = brickFactory.createLBrick(4, 4, 1, 1);
        buildTool.placeBrick(test);
        return buildTool.getModel();
    }

    public static void main(String[] args)
    {
        double unit = 10;
        JavaCSG csg = JavaCSGFactory.createNoCaching();
        MockupModelFactoryImpl modelFactory = new MockupModelFactoryImpl(csg, unit);
        Model test = modelFactory.createModel(new TestDesign());
        ModelViewer viewer = new ModelViewerImpl(csg);
        viewer.view(test);
    }
}

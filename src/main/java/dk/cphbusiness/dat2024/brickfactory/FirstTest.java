package dk.cphbusiness.dat2024.brickfactory;

import dk.cphbusiness.dat2024.brickfactory.building.*;
import dk.cphbusiness.dat2024.brickfactory.building.impl.MockupModelFactoryImpl;
import dk.cphbusiness.dat2024.brickfactory.building.impl.ModelViewerImpl;
import dk.cphbusiness.dat2024.brickfactory.building.models.Tower;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class FirstTest
{
    public static void main(String[] args)
    {
        double unit = 8;
        JavaCSG csg = JavaCSGFactory.createNoCaching();
        MockupModelFactoryImpl modelFactory = new MockupModelFactoryImpl(csg, unit);
        Model tower = modelFactory.createModel(new Tower());
        ModelViewer viewer = new ModelViewerImpl(csg);
        viewer.view(tower);
    }
}

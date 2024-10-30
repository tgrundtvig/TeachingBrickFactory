package dk.cphbusiness.dat2024.brickfactory.building.impl;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.building.Model;
import dk.cphbusiness.dat2024.brickfactory.building.ModelViewer;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

import java.util.ArrayList;
import java.util.List;

public class ModelViewerImpl implements ModelViewer
{
    private final JavaCSG csg;

    public ModelViewerImpl(JavaCSG csg)
    {
        this.csg = csg;
    }

    @Override
    public void view(Model model)
    {
        List<Geometry3D> geometryList = new ArrayList<>();
        for(Brick brick : model.getBricks())
        {
            geometryList.add(brick.getGeometry());
        }
        Geometry3D res = csg.union3D(geometryList);
        csg.view(res);
    }
}

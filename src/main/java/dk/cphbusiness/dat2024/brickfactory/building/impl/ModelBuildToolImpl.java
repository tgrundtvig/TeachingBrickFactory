package dk.cphbusiness.dat2024.brickfactory.building.impl;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.building.Model;
import dk.cphbusiness.dat2024.brickfactory.building.ModelBuildTool;
import dk.cphbusiness.dat2024.brickfactory.factory.BrickImpl;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

import java.util.ArrayList;
import java.util.List;

public class ModelBuildToolImpl implements ModelBuildTool
{
    private final JavaCSG csg;
    private final double unit;
    private final List<Brick> placedBricks;

    public ModelBuildToolImpl(JavaCSG csg, double unit)
    {
        this.csg = csg;
        this.unit = unit;
        this.placedBricks = new ArrayList<>();
    }

    @Override
    public Brick translate(int x, int y, int z, Brick brick)
    {
        Geometry3D original = brick.getGeometry();
        Geometry3D translated = csg.translate3D(x*unit, y*unit, z*unit).transform(original);
        return new BrickImpl(translated);
    }

    @Override
    public Brick rotate(int clicks, Brick brick)
    {
        if(clicks < -4 || clicks > 4)
        {
            throw new IllegalArgumentException("clicks must be in the range: [-4;4]!");
        }
        Geometry3D original = brick.getGeometry();
        Geometry3D rotated = csg.rotate3DZ(csg.rotations(-0.25*clicks)).transform(original);
        return new BrickImpl(rotated);
    }

    @Override
    public void placeBrick(Brick brick)
    {
        placedBricks.add(brick);
    }

    @Override
    public Model getModel()
    {
        return new ModelImpl(placedBricks);
    }
}

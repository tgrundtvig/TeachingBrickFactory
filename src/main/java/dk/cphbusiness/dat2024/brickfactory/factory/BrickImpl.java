package dk.cphbusiness.dat2024.brickfactory.factory;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import org.abstractica.javacsg.Geometry3D;

public class BrickImpl implements Brick
{
    private final Geometry3D geometry;

    public BrickImpl(Geometry3D geometry)
    {
        this.geometry = geometry;
    }

    @Override
    public Geometry3D getGeometry()
    {
        return geometry;
    }
}

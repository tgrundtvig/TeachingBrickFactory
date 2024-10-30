package dk.cphbusiness.dat2024.brickfactory.factory;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class MockupBrickFactory implements BrickFactory
{
    private final JavaCSG csg;
    private final double unit;

    public MockupBrickFactory(JavaCSG csg, double unit)
    {
        this.csg = csg;
        this.unit = unit;
    }

    @Override
    public Brick createBasicBrick(int xSize, int ySize, int zSize)
    {
        Geometry3D res = csg.box3D(xSize*unit, ySize*unit, zSize*unit, false);
        res = csg.translate3D(0.5*unit*xSize, 0.5*unit*ySize, 0).transform(res);
        return new BrickImpl(res);
    }

    @Override
    public Brick createLBrick(int xSize, int ySize, int width, int zSize)
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public Brick createUBrick(int xSize, int ySize, int width, int zSize)
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public Brick createOBrick(int xSize, int ySize, int width, int zSize)
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public Brick createHBrick(int xSize, int ySize, int width, int midSectionOffset, int zSize)
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}

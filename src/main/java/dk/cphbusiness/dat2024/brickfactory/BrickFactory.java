package dk.cphbusiness.dat2024.brickfactory;

import org.abstractica.javacsg.Geometry3D;


//All bricks will be delivered with lower left corner at (0,0,0);
public interface BrickFactory
{
    Brick createBasicBrick(int xSize, int ySize, int zSize);
    Brick createLBrick(int xSize, int ySize, int width, int zSize);
    Brick createUBrick(int xSize, int ySize, int width, int zSize);
    Brick createOBrick(int xSize, int ySize, int width, int zSize);
    Brick createHBrick(int xSize, int ySize, int width, int midSectionOffset, int zSize);
}

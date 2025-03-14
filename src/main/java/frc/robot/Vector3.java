package frc.robot;

public class Vector3 {
    public double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 subtract(Vector3 other) {
         return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }
  
    public double dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }
  
    public double magnitude() {
        return (double) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        double mag = magnitude();
        return new Vector3(x / mag, y / mag, z / mag);
    }
}
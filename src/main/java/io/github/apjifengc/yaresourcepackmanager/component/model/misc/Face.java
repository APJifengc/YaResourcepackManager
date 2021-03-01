package io.github.apjifengc.yaresourcepackmanager.component.model.misc;

import io.github.apjifengc.yaresourcepackmanager.component.model.enums.CullFace;
import io.github.apjifengc.yaresourcepackmanager.component.texture.Texture;
import io.github.apjifengc.yaresourcepackmanager.component.texture.VariableTexture;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Contains the properties of the specified face. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
@Data
public class Face {
    private Integer x1, y1, x2, y2;
    private Texture texture;
    private CullFace cullFace;
    private Integer rotation;
    private Integer tintIndex;

    /**
     * Create a face in the model.
     *
     * @param texture Specifies the texture in form of {@link Texture} or can be another {@link
     *                VariableTexture texture variable}
     */
    public Face(Texture texture) {
        this(texture, null, null);
    }

    /**
     * Create a face in the model.
     *
     * @param texture  Specifies the texture in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param rotation Rotates the texture by the specified number of degrees. Can be 0, 90, 180, or 270.
     *                 Defaults to 0. Rotation does not affect which part of the texture is used. Instead,
     */
    public Face(Texture texture,
                Integer rotation) {
        this(texture, null, rotation);
    }

    /**
     * Create a face in the model.
     *
     * @param texture  Specifies the texture in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param cullFace Specifies whether a face does not need to be rendered when there is a block touching it
     *                 in the specified position. It also determines the side of the block to use the light
     *                 level from for lighting the face, and if unset, defaults to the side.
     */
    public Face(Texture texture, CullFace cullFace) {
        this(texture, cullFace, null);
    }

    /**
     * Create a face in the model.
     *
     * @param texture  Specifies the texture in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param cullFace Specifies whether a face does not need to be rendered when there is a block touching it
     *                 in the specified position. It also determines the side of the block to use the light
     *                 level from for lighting the face, and if unset, defaults to the side.
     * @param rotation Rotates the texture by the specified number of degrees. Can be 0, 90, 180, or 270.
     *                 Defaults to 0. Rotation does not affect which part of the texture is used. Instead,
     *                 it amounts to permutation of the selected texture vertexes (selected implicitly, or
     */
    public Face(Texture texture, CullFace cullFace,
                Integer rotation) {
        this(null, null, null, null, texture, cullFace, rotation);
    }

    /**
     * Create a face in the model.
     *
     * @param x1      Defines the area of the texture to use according to the scheme <code>[x1, y1, x2, y2]
     *                </code>. If unset, it defaults to values equal to xyz position of the element. The
     *                texture behavior is inconsistent if UV extends below 0 or above 16. If the numbers
     *                of <code>x1</code> and <code>x2</code> are swapped (e.g. from <code>0, 0, 16, 16
     *                </code> to <code>16, 0, 0, 16</code>), the texture flips. UV is optional, and if
     *                not supplied it automatically generates based on the element's position.
     * @param texture Specifies the texture in form of {@link Texture} or can be another {@link
     *                VariableTexture texture variable}
     */
    public Face(Integer x1, Integer y1, Integer x2, Integer y2, Texture texture) {
        this(x1, y1, x2, y2, texture, null, null);
    }

    /**
     * Create a face in the model.
     *
     * @param x1       Defines the area of the texture to use according to the scheme <code>[x1, y1, x2, y2]
     *                 </code>. If unset, it defaults to values equal to xyz position of the element. The
     *                 texture behavior is inconsistent if UV extends below 0 or above 16. If the numbers
     *                 of <code>x1</code> and <code>x2</code> are swapped (e.g. from <code>0, 0, 16, 16
     *                 </code> to <code>16, 0, 0, 16</code>), the texture flips. UV is optional, and if
     *                 not supplied it automatically generates based on the element's position.
     * @param texture  Specifies the texture in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param rotation Rotates the texture by the specified number of degrees. Can be 0, 90, 180, or 270.
     *                 Defaults to 0. Rotation does not affect which part of the texture is used. Instead,
     *                 it amounts to permutation of the selected texture vertexes (selected implicitly, or
     */
    public Face(Integer x1, Integer y1, Integer x2, Integer y2, Texture texture,
                Integer rotation) {
        this(x1, y1, x2, y2, texture, null, rotation);
    }

    /**
     * Create a face in the model.
     *
     * @param x1       Defines the area of the texture to use according to the scheme <code>[x1, y1, x2, y2]
     *                 </code>. If unset, it defaults to values equal to xyz position of the element. The
     *                 texture behavior is inconsistent if UV extends below 0 or above 16. If the numbers
     *                 of <code>x1</code> and <code>x2</code> are swapped (e.g. from <code>0, 0, 16, 16
     *                 </code> to <code>16, 0, 0, 16</code>), the texture flips. UV is optional, and if
     *                 not supplied it automatically generates based on the element's position.
     * @param texture  Specifies the texture in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param cullFace Specifies whether a face does not need to be rendered when there is a block touching it
     *                 in the specified position. It also determines the side of the block to use the light
     *                 level from for lighting the face, and if unset, defaults to the side.
     */
    public Face(Integer x1, Integer y1, Integer x2, Integer y2, Texture texture, CullFace cullFace) {
        this(x1, y1, x2, y2, texture, cullFace, null);
    }

    /**
     * Create a face in the model.
     *
     * @param x1       Defines the area of the texture to use according to the scheme <code>[x1, y1, x2, y2]
     *                 </code>. If unset, it defaults to values equal to xyz position of the element. The
     *                 texture behavior is inconsistent if UV extends below 0 or above 16. If the numbers
     *                 of <code>x1</code> and <code>x2</code> are swapped (e.g. from <code>0, 0, 16, 16
     *                 </code> to <code>16, 0, 0, 16</code>), the texture flips. UV is optional, and if
     *                 not supplied it automatically generates based on the element's position.
     * @param texture  Specifies the texture in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param cullFace Specifies whether a face does not need to be rendered when there is a block touching it
     *                 in the specified position. It also determines the side of the block to use the light
     *                 level from for lighting the face, and if unset, defaults to the side.
     * @param rotation Rotates the texture by the specified number of degrees. Can be 0, 90, 180, or 270.
     *                 Defaults to 0. Rotation does not affect which part of the texture is used. Instead,
     *                 it amounts to permutation of the selected texture vertexes (selected implicitly, or
     *                 explicitly though <code>uv</code>).
     */
    public Face(Integer x1, Integer y1, Integer x2, Integer y2, Texture texture, CullFace cullFace,
                Integer rotation) {
        this(x1, y1, x2, y2, texture, cullFace, rotation, null);
    }

    /**
     * Create a face in the model.
     *
     * @param x1        Defines the area of the texture to use according to the scheme <code>[x1, y1, x2, y2]
     *                  </code>. If unset, it defaults to values equal to xyz position of the element. The
     *                  texture behavior is inconsistent if UV extends below 0 or above 16. If the numbers
     *                  of <code>x1</code> and <code>x2</code> are swapped (e.g. from <code>0, 0, 16, 16
     *                  </code> to <code>16, 0, 0, 16</code>), the texture flips. UV is optional, and if
     *                  not supplied it automatically generates based on the element's position.
     * @param texture   Specifies the texture in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param cullFace  Specifies whether a face does not need to be rendered when there is a block touching it
     *                  in the specified position. It also determines the side of the block to use the light
     *                  level from for lighting the face, and if unset, defaults to the side.
     * @param rotation  Rotates the texture by the specified number of degrees. Can be 0, 90, 180, or 270.
     *                  Defaults to 0. Rotation does not affect which part of the texture is used. Instead,
     *                  it amounts to permutation of the selected texture vertexes (selected implicitly, or
     *                  explicitly though <code>uv</code>).
     * @param tintIndex Determines whether to tint the texture using a hardcoded tint index. The default
     *                  is not using the tint, and any number causes it to use tint. Note that only certain
     *                  blocks have a tint index; all others are unaffected.
     */
    public Face(Integer x1, Integer y1, Integer x2, Integer y2, Texture texture, CullFace cullFace,
                Integer rotation, Integer tintIndex) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.texture = texture;
        this.cullFace = cullFace;
        this.rotation = rotation;
        this.tintIndex = tintIndex;
    }
}

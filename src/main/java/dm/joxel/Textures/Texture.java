package dm.joxel.Textures;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;


public class Texture {
	public static int openImageToTexture(String file) throws IOException {
			PNGDecoder decoder = new PNGDecoder(new FileInputStream(file));

			ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
			buf.flip();

			int id = GL11.glGenTextures();

	        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

		    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
	        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);

			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

	        return id;	
	}	
}

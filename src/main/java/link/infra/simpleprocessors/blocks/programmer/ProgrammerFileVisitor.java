package link.infra.simpleprocessors.blocks.programmer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;

public class ProgrammerFileVisitor extends SimpleFileVisitor<Path> {
	public int currentSize = 0;
	public HashMap<String, String> pendingMap = new HashMap<String, String>();
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		currentSize += Files.size(file);
		if (currentSize > 64000) {
			// won't fit on biggest processor
			return FileVisitResult.TERMINATE;
		}
		System.out.println(file);
		List<String> fileStringList = Files.readAllLines(file, StandardCharsets.UTF_8);
		Path testDir = Paths.get(ProgrammerTileEntity.TEST_DIR);
		pendingMap.put(testDir.relativize(file).toString(), String.join("\n", fileStringList));
		return FileVisitResult.CONTINUE;
	}
}

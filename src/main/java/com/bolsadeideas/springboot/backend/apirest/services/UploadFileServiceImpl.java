package com.bolsadeideas.springboot.backend.apirest.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileServiceImpl implements IUpoaldFileService{

	private final Logger log = org.slf4j.LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	private final static String DIRECTORIO_UPLOAD = "upload";
	
	
	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		// TODO Auto-generated method stub
		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString());
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		
		if(!recurso.exists() && recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resource/static/images").resolve("no_user.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
			log.error("Error No se pudo cargar la Imagen"+ nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		String nombrearhivo= UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ","");
		Path rutaArchivo = getPath(nombrearhivo);
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
	
		
		return nombrearhivo;
	}

	@Override
	public boolean eliminar(String nombrefoto) {
		if(nombrefoto!=null && nombrefoto.length()>0 ) {
			Path rutaFotoanterior = Paths.get("upload").resolve(nombrefoto).toAbsolutePath();
			File archivoFotoanterior = rutaFotoanterior.toFile();
			if(archivoFotoanterior.exists() && archivoFotoanterior.canRead()) {
				archivoFotoanterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}
	

}

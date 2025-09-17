package com.betacom.com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.com.response.ResponseBase;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/files")
@CrossOrigin("*")
public class UploadController {
	
	 @Value("${app.upload.dir}")
	 private String uploadDir;
	 
	 // per mette l'immagine nella cartella images e ritorna il nome del file randomizzato
	 @PostMapping("/upload")
	 public String upload(@RequestParam("file") MultipartFile file) throws IOException {
		 log.debug("uploadFile");
		 // estrae l'estensione
		 String ext = Optional.ofNullable(file.getOriginalFilename())
			        .filter(f -> f.contains("."))
			        .map(f -> f.substring(f.lastIndexOf('.') + 1))
			        .orElse("png");
		 
		 // crea un path assoluto dal relativo e crea cartella se non esiste
		 Path dir = Path.of(uploadDir).toAbsolutePath();
		 Files.createDirectories(dir);
		 
		 // crea un nome random e lo mette il file nella cartella
		 String filename = UUID.randomUUID() + "." + ext;
		 Path dest = dir.resolve(filename);
		 file.transferTo(dest.toFile()); // .toFile() converte il path in file
		 
		 return filename;
	 }
	 
	 // elimina il file nel caso l'insert dei dati della foto non sia andato a buon fine
	 @PostMapping("/delete")
	 public ResponseBase deleteFile(@RequestParam String filename) {
		 log.debug("deleteFile");
		 ResponseBase r = new ResponseBase();
		 try {
			 Path filePath = Path.of(uploadDir).resolve(filename);
		     Files.deleteIfExists(filePath);
		     r.setRc(true);
		     r.setMsg("file eliminato");
		} catch (IOException e) {
			r.setRc(false);
			r.setMsg("Errore durante l'eliminazione");
		}
		 return r;
	 }
}

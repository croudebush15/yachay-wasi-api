package com.pe.proyectotechnologico.Controller;


import com.pe.proyectotechnologico.Model.Material;
import com.pe.proyectotechnologico.Service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;


    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<Material>> getMaterials(){
        return new ResponseEntity<>(materialService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterial(@PathVariable Integer id){
        Material currentMaterial = materialService.findById(id);
        if( null == currentMaterial){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentMaterial, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Material> postMaterial(@RequestBody Material material){
        materialService.create(material);
        return new ResponseEntity<>(material,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Material> putMaterial(@PathVariable Integer id,
                                            @RequestBody Material material){

        if( null == materialService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        material.setId(id);
        materialService.update(material);
        return new ResponseEntity<>(material,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Material> deleteMaterial(@PathVariable Integer id){
        if( null == materialService.findById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

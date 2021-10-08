package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.Material;
import com.pe.proyectotechnologico.Repository.MaterialRepository;

import java.util.List;

public class MaterialService implements CrudService<Material,Integer> {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void create(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void update(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void delete(Integer id) {
        materialRepository.deleteById(id);
    }

    @Override
    public Material findById(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }

    @Override
    public List<Material> findAll() {
       return materialRepository.findAll();
    }
}

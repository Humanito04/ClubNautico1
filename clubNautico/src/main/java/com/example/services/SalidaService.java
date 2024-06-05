package com.example.services;

import com.example.personas.Salida;
import com.example.repositories.SalidaRepository;
import com.example.services.dto.SalidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalidaService {

    @Autowired
   private SalidaRepository salidaRepository;

    private final ModelMapper MODELMAPPER = new ModelMapper();

    public SalidaDTO convertToDto(Salida salida){
        return MODELMAPPER.map(salida,SalidaDTO.class);
    }

    public Salida convertToEntity(SalidaDTO salidaDTO){
        return MODELMAPPER.map(salidaDTO,Salida.class);
    }


   public Salida crearSalida(SalidaDTO salidaDTO){
        Salida salida = convertToEntity(salidaDTO);
        Salida salidaS = this.salidaRepository.save(salida);
       return salidaS;
   }

   public Optional<Salida> findSalidaById(Integer idSalida){
       return this.salidaRepository.findById(idSalida);
   }

   public SalidaDTO actualizarSalida(SalidaDTO salidaDTO, Integer idSalida){
       if (idSalida == null) {
           throw new IllegalArgumentException("IdSalida cannot be null");
       }
       Salida salidaUpdate = this.salidaRepository.findById(idSalida).orElseThrow(()
               ->new RuntimeException("Salida not found with id" + idSalida));

       BeanUtils.copyProperties(salidaDTO, salidaUpdate);
       Salida actualizaSalida = this.salidaRepository.save(salidaUpdate);

       return convertToDto(salidaUpdate);
   }

   public List<Salida> listarSalida(){
       return this.salidaRepository.findAll();
   }

   public void borrarSalida(Salida salida){
       this.salidaRepository.delete(salida);
   }
}
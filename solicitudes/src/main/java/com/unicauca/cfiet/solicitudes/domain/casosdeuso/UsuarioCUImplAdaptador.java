package com.unicauca.cfiet.solicitudes.domain.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.UsuarioCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.UsuarioGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.TipoUsuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioLiviano;
import com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones.MensajesError;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementación de la interfaz de los casos de uso para la gestión de usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class UsuarioCUImplAdaptador implements UsuarioCUIntPuerto {
    private final UsuarioGatewayIntPuerto gateway;
    private final ExcepcionesFormateadorIntPuerto formateadorExcepciones;
    /* Constantes */
    private static final String USUARIOS = "usuarios";
    private static final String USUARIO = "Usuario";
    private static final String NUMERO_DOCUMENTO = "Numero de documento";
    private static final String CORREO_ELECTRONICO = "Correo Electrónico";
    private  static final String USERNAME = "username";
    private static final String TIPO_USUARIO = "Tipo de usuario";
    private static final String NOMBRE = "nombre";

    public UsuarioCUImplAdaptador(UsuarioGatewayIntPuerto gateway, ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        this.gateway = gateway;
        this.formateadorExcepciones = formateadorExcepciones;
    }

    @Override
    public List<UsuarioLiviano> getUsuarios(int pagina, int tamanio) {
        if(pagina < 0 || tamanio < 0)
            formateadorExcepciones.lanzarMalFormato(MensajesError.PAGINACION_ERROR);
        List<UsuarioLiviano> usuarios = gateway.getUsuarios(pagina, tamanio);
        if(usuarios.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, USUARIOS));
        return  usuarios;
    }

    @Override
    public Usuario getUsuario(String uuid) {
        Usuario usuario = gateway.getUsuario(uuid);
        if(usuario == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, USUARIO, uuid));
        return usuario;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario, String tipoUsuario) {
        if(gateway.existeUsuarioNumeroDocumento(usuario.getNumeroDocumento()))
            formateadorExcepciones.lanzarEntidadExiste(String.format(MensajesError.ATRIBUTO_UNICO_YA_EXISTE, USUARIO, NUMERO_DOCUMENTO, usuario.getNumeroDocumento()));
        if(gateway.existeUsuarioCorreo(usuario.getCorreoElectronico()))
            formateadorExcepciones.lanzarEntidadExiste(String.format(MensajesError.ATRIBUTO_UNICO_YA_EXISTE, USUARIO, CORREO_ELECTRONICO, usuario.getCorreoElectronico()));
        if(gateway.existeUsuarioUsername(usuario.getUsername()))
            formateadorExcepciones.lanzarEntidadExiste(String.format(MensajesError.ATRIBUTO_UNICO_YA_EXISTE, USUARIO, USERNAME, usuario.getUsername()));

        TipoUsuario objTipoUsuario = gateway.getTipoUsuarioPorNombre(usuario.getObjTipoUsuario().getNombre());
        if(objTipoUsuario == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA_FILTRO, TIPO_USUARIO,  NOMBRE, usuario.getObjTipoUsuario().getNombre()));

        if(usuario.tieneRolesDuplicados())
            formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.ROLES_DUPLICADOS_USUARIO);
        if(!usuario.rolesSonValidos(gateway.getRoles()))
            formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.ROLES_NO_VALIDOS);

        usuario.setUuidUsuario(UUID.randomUUID().toString());
        usuario.setObjTipoUsuario(objTipoUsuario);
        Usuario instancia = usuario.crearInstancia(tipoUsuario);
        if(instancia == null)
            formateadorExcepciones.lanzarReglaNegocioViolada(String.format(MensajesError.INSTANCIA_NO_VALIDA, USUARIO));

        return gateway.guardarUsuario(instancia);
    }

    @Override
    public List<Usuario> crearUsuarios(List<Usuario> usuarios) {
        List<Usuario> usuariosRespuesta = new ArrayList<>();
        if(usuarios.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.ARCHIVO_EXCEL_VACIO, USUARIOS));

        for(Usuario usuario: usuarios)
            usuariosRespuesta.add(crearUsuario(usuario, usuario.getRoles().get(0).getNombre().replace(" ", "")));

        return  usuariosRespuesta;
    }

    @Override
    public Usuario actualizarUsuario(String uuid, Usuario usuario) {
        Usuario usuarioObtenido = gateway.getUsuario(uuid);
        if(usuarioObtenido == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, USUARIO, uuid));

        if(usuario.tieneRolesDuplicados())
            formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.ROLES_DUPLICADOS_USUARIO);
        if(!usuario.rolesSonValidos(gateway.getRoles()))
            formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.ROLES_NO_VALIDOS);

        if(!usuarioObtenido.getNumeroDocumento().equals(usuario.getNumeroDocumento())){
            if(gateway.existeUsuarioNumeroDocumento(usuario.getNumeroDocumento()))
                formateadorExcepciones.lanzarEntidadExiste(String.format(MensajesError.ATRIBUTO_UNICO_YA_EXISTE, USUARIO, NUMERO_DOCUMENTO, usuario.getNumeroDocumento()));
        }

        if(!usuarioObtenido.getCorreoElectronico().equals(usuario.getCorreoElectronico())){
            if(gateway.existeUsuarioCorreo(usuario.getCorreoElectronico()))
                formateadorExcepciones.lanzarEntidadExiste(String.format(MensajesError.ATRIBUTO_UNICO_YA_EXISTE, USUARIO, CORREO_ELECTRONICO, usuario.getCorreoElectronico()));
        }

        if(!usuarioObtenido.getUsername().equals(usuario.getUsername())) {
            if (gateway.existeUsuarioUsername(usuario.getUsername()))
                formateadorExcepciones.lanzarEntidadExiste(String.format(MensajesError.ATRIBUTO_UNICO_YA_EXISTE, USUARIO, USERNAME, usuario.getUsername()));
        }

        usuarioObtenido.actualizarUsuario(usuario);
        return gateway.guardarUsuario(usuarioObtenido);
    }
}

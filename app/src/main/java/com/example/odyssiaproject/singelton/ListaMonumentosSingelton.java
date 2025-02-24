package com.example.odyssiaproject.singelton;

import android.util.Log;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;

import java.util.ArrayList;
import java.util.List;

public class ListaMonumentosSingelton {
    private static ListaMonumentosSingelton instance;

    private List<Monumentos> listaMonumentos;
    private ListaMonumentosSingelton() {
        super();
    }
    public static ListaMonumentosSingelton getInstance() {
        if (instance == null) {
            instance = new ListaMonumentosSingelton();
        }
        return instance;
    }
    public Monumentos getMonumentoByName(String nombre) {
        for (Monumentos m : listaMonumentos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }
    public void inicializar() {
        listaMonumentos = new ArrayList<>();
        agregarMonumento("Plaza Mayor","Madrid","GRATIS","24h/dia");
        agregarMonumento("Puerta de Alcalá","Madrid","GRATIS","24h/dia");
        agregarMonumento("Parque de El Retiro","Madrid","GRATIS","6:00-22:00");

        agregarMonumento("Basílica de la Sagrada Familia","Barcelona","Desde 26€","9:00-20:00");
        agregarMonumento("Parque Güell","Barcelona","Desde 10€","9:30-19:30");
        agregarMonumento("Casa Batlló","Barcelona","Desde 10€","9:00-20:00");

        agregarMonumento("Catedral de Sevilla y La Giralda","Sevilla","Desde 14€","10:45 a 17:00");
        agregarMonumento("Real Alcázar de Sevilla","Sevilla","Desde 14.50€","9:30 a 17:00");
        agregarMonumento("Plaza de España","Sevilla","GRATIS","24/dia");

        agregarMonumento("Catedral de Nuestra Señora", "Amberes", "Desde 12€", "10:00-17:00");
        agregarMonumento("Museo Plantin-Moretus", "Amberes", "Desde 12€", "10:00-17:00");
        agregarMonumento("Castillo de Steen", "Amberes", "GRATIS", "10:00-18:00");

        agregarMonumento("Campanario de Brujas", "Brujas", "Desde 14€", "9:30-18:00");
        agregarMonumento("Basílica de la Santa Sangre", "Brujas", "GRATIS", "9:30-12:00-14:00-17:00");
        agregarMonumento("Hospital de San Juan y Museo Memling", "Brujas", "Desde 12€", "9:30-17:00");

        agregarMonumento("Atomium", "Bruselas", "Desde 16€", "10:00-18:00");
        agregarMonumento("Manneken Pis", "Bruselas", "GRATIS", "24/dia");
        agregarMonumento("Palacio Real de Bruselas", "Bruselas", "GRATIS", "10:30-15:45");

        agregarMonumento("Basílica de Notre-Dame de Fourvière", "Lyon", "GRATIS", "7:00-19:00");
        agregarMonumento("Teatros Romanos de Fourvière", "Lyon", "GRATIS", "7:00-19:00");
        agregarMonumento("Parque de la Tête d'Or", "Lyon", "GRATIS", "6:30-22:30");

        agregarMonumento("Basílica de Notre-Dame de la Garde", "Marsella", "GRATIS", "7:00-18:15");
        agregarMonumento("MUCEM", "Marsella", "Desde 11€", "10:00-18:00");
        agregarMonumento("Fuerte de San Juan", "Marsella", "GRATIS", "10:00-20:00");

        agregarMonumento("Torre Eiffel", "París", "Desde 10€", "9:30-23:45");
        agregarMonumento("Catedral de Notre-Dame", "París", "N/A", "Temporalmente cerrada");
        agregarMonumento("Arco de Triunfo", "París", "13€", "10:00-23:00");

        agregarMonumento("Acrópolis de Atenas", "Atenas", "Desde 20€", "8:00-20:00");
        agregarMonumento("Museo de la Acrópolis", "Atenas", "Desde 10€", "8:00-16:00");
        agregarMonumento("Ágora Antigua", "Atenas", "Desde 30 €", "8:00-20:00");

        agregarMonumento("Torre Blanca", "Salónica", "Desde 4€", "8:00-20:00");
        agregarMonumento("Arco de Galerio", "Salónica", "GRATIS", "24/dia");
        agregarMonumento("Rotonda de San Jorge", "Salónica", "Desde 6€", "8:00-15:00");

        agregarMonumento("Museo Arqueológico de Thera", "Santorini", "Desde 6€", "08:30-15:30");
        agregarMonumento("Sitio Arqueológico de Akrotiri", "Santorini", "Desde 12 €", "08:00-15:00");
        agregarMonumento("Castillo de Oia", "Santorini", "GRATIS", "24/dia");
        // Imprime el último monumento agregado en los logs.
        Log.i("ListaPromocionesSingleton", "tamaño lista" + listaMonumentos.size());
    }

    private void agregarMonumento(String nombreMonumento, String nombreCiudad, String precio, String horario) {
        Monumentos monumentos = new Monumentos();
        monumentos.setNombre(nombreMonumento);
        monumentos.setHorario(precio);
        monumentos.setPrecio(horario);

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(nombreCiudad);
        monumentos.setCiudad(ciudad);

        listaMonumentos.add(monumentos);
    }
    private List<Monumentos> obtenerMonumentosPorCiudad(String nombreCiudad, List<Monumentos> monumentos) {
        List<Monumentos> resultado = new ArrayList<>();
        if (nombreCiudad.equals("Madrid")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Plaza Mayor") ||
                        m.getNombre().equals("Puerta de Alcalá") ||
                        m.getNombre().equals("Parque de El Retiro")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Barcelona")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Basílica de la Sagrada Familia") ||
                        m.getNombre().equals("Parque Güell") ||
                        m.getNombre().equals("Casa Batlló")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Sevilla")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Catedral de Sevilla y La Giralda") ||
                        m.getNombre().equals("Real Alcázar de Sevilla") ||
                        m.getNombre().equals("Plaza de España")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Amberes")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Catedral de Nuestra Señora") ||
                        m.getNombre().equals("Museo Plantin-Moretus") ||
                        m.getNombre().equals("Castillo de Steen")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Brujas")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Campanario de Brujas") ||
                        m.getNombre().equals("Basílica de la Santa Sangre") ||
                        m.getNombre().equals("Hospital de San Juan y Museo Memling")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Bruselas")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Atomium") ||
                        m.getNombre().equals("Manneken Pis") ||
                        m.getNombre().equals("Palacio Real de Bruselas")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Lyon")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Basílica de Notre-Dame de Fourvière") ||
                        m.getNombre().equals("Teatros Romanos de Fourvière") ||
                        m.getNombre().equals("Parque de la Tête d'Or")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Marsella")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Basílica de Notre-Dame de la Garde") ||
                        m.getNombre().equals("MUCEM") ||
                        m.getNombre().equals("Fuerte de San Juan")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Paris")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Torre Eiffel") ||
                        m.getNombre().equals("Catedral de Notre-Dame") ||
                        m.getNombre().equals("Arco de Triunfo")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Atenas")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Acrópolis de Atenas") ||
                        m.getNombre().equals("Museo de la Acrópolis") ||
                        m.getNombre().equals("Ágora Antigua")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Salonica")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Torre Blanca") ||
                        m.getNombre().equals("Arco de Galerio") ||
                        m.getNombre().equals("Rotonda de San Jorge")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Santorini")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Museo Arqueológico de Thera") ||
                        m.getNombre().equals("Sitio Arqueológico de Akrotiri") ||
                        m.getNombre().equals("Castillo de Oia")) {
                    resultado.add(m);
                }
            }
        }else if (nombreCiudad.equals("Roma")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Coliseo Romano") ||
                        m.getNombre().equals("Fontana di Trevi") ||
                        m.getNombre().equals("Basílica de San Pedro")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Venecia")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Plaza de San Marcos y Basílica de San Marcos") ||
                        m.getNombre().equals("Palacio Ducal") ||
                        m.getNombre().equals("Puente de Rialto")) {
                    resultado.add(m);
                }
                }
            } else if (nombreCiudad.equals("Florencia")) {
                for (Monumentos m : monumentos) {
                    if (m.getNombre().equals("Catedral de Santa María del Fiore") ||
                            m.getNombre().equals("Galería Uffizi") ||
                            m.getNombre().equals("Palacio Pitti y Jardines de Boboli")) {
                        resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Bergen")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Bryggen (Barrio Hanseático)") ||
                        m.getNombre().equals("Monte Fløyen") ||
                        m.getNombre().equals("Iglesia de Santa María (Mariakirken)")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Oslo")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Museo de Barcos Vikingos") ||
                        m.getNombre().equals("Parque de Esculturas de Gustav Vigeland") ||
                        m.getNombre().equals("Palacio Real")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Tromso")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Catedral del Ártico (Ishavskatedralen)") ||
                        m.getNombre().equals("Museo Polar") ||
                        m.getNombre().equals("Montaña Storsteinen")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Braga")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Santuario del Bom Jesús do Monte") ||
                        m.getNombre().equals("Catedral de Braga (Sé de Braga)") ||
                        m.getNombre().equals("Arco de la Porta Nova")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Lisboa")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Torre de Belém") ||
                        m.getNombre().equals("Monasterio de los Jerónimos") ||
                        m.getNombre().equals("Plaza del Comercio")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Porto")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Puente de Luis I") ||
                        m.getNombre().equals("Torre de los Clérigos") ||
                        m.getNombre().equals("Iglesia de San Francisco")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Zurich")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Zytglogge (Torre del Reloj)") ||
                        m.getNombre().equals("Münster de Berna") ||
                        m.getNombre().equals("Fuente de la Justicia")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Ginebra")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Lago de Ginebra") ||
                        m.getNombre().equals("Jet d'Eau") ||
                        m.getNombre().equals("Catedral de San Pedro")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Berna")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Zytglogge (Torre del Reloj)") ||
                        m.getNombre().equals("Münster de Berna") ||
                        m.getNombre().equals("Fuente de la Justicia")) {
                    resultado.add(m);
                }
            }
        }else if (nombreCiudad.equals("Ámsterdam")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Museo Van Gogh") ||
                        m.getNombre().equals("Rijksmuseum") ||
                        m.getNombre().equals("Casa de Ana Frank")) {
                    resultado.add(m);
                }
            }
        } else  if (nombreCiudad.equals("Róterdam")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Casas Cubo (Kubuswoningen)") ||
                        m.getNombre().equals("Euromast") ||
                        m.getNombre().equals("Markthal (Mercado Central)")) {
                    resultado.add(m);
                }
            }
        }else if (nombreCiudad.equals("Utrecht")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Torre Dom (Domtoren)") ||
                        m.getNombre().equals("Castillo de Haar (Kasteel de Haar)") ||
                        m.getNombre().equals("Museo del Ferrocarril (Spoorwegmuseum)")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Liverpool")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Catedral de Liverpool") ||
                        m.getNombre().equals("The Beatles Story") ||
                        m.getNombre().equals("Royal Albert Dock")) {
                    resultado.add(m);
                }
            }
        }else if (nombreCiudad.equals("Londres")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Torre de Londres") ||
                        m.getNombre().equals("Abadía de Westminster") ||
                        m.getNombre().equals("Palacio de Buckingham")) {
                    resultado.add(m);
                }
            }
        } else if (nombreCiudad.equals("Mánchester")) {
            for (Monumentos m : monumentos) {
                if (m.getNombre().equals("Catedral de Mánchester") ||
                        m.getNombre().equals("Biblioteca John Rylands") ||
                        m.getNombre().equals("Museo de Ciencia e Industria (Science and Industry Museum)")) {
                    resultado.add(m);
                }
            }
        }


        return resultado;
    }
    public List<Monumentos> getListaMonumentos() {
        return listaMonumentos;
    }
}

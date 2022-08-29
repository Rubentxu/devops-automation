export const CATALOGUE = [
  {
    id: "aaaa",
    name: "Listar ficheros en etc",
    description: "Listamos los ficheros del contenedor en directorio etc",
    priority: 1,
    entry_point: ["sh", "-c", "find /etc -print"]
  },
  {
    id: "bbbb",
    name: "Listar ficheros en raiz",
    description: "Listamos los ficheros del contenedor en directorio raiz",
    priority: 1,
    entry_point: ["sh", "-c", "find / -print"]
  },
  {
    id: "cccc",
    name: "Listar ficheros en root",
    description: "Listamos los ficheros del contenedor en directorio root",
    priority: 1,
    entry_point: ["sh", "-c", "find /root -print"]
  },
  {
    id: "dddd",
    name: "Crear directorio",
    description: "Crear directorio en /tmp/pruebas dentro del contenedor",
    priority: 1,
    entry_point: ["sh", "-c", "mkdir -p /tmp/pruebas && ls -la /tmp"]
  },
  {
    id: "eeee",
    name: "Copiar ficheros",
    description: "Copiamos los ficheros del directorio /root a /tmp/root contenedor",
    priority: 1,
    entry_point: ["sh", "-c", "cp /root /tmp && ls -la /tmp "]
  }
]


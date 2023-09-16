from fastapi import FastAPI,HTTPException,Request
from datetime import datetime, timedelta
from typing import List, Optional, Dict
from pydantic import BaseModel

class Ciudadano(BaseModel):
    nombre: str

class Establecimiento(BaseModel):
    nombre: str

class Servicio(BaseModel):
    nombre: str

class Comunidad(BaseModel):
    nombre: str
    miembros: List[Ciudadano]
    gradoDeConfianza: float
    establecimientos: List[Establecimiento]
    servicios: List[Servicio]

app = FastAPI()

comunidad_fusionable : Comunidad
comunidad_principal : Comunidad

@app.post("/propose_fusion/", response_model=Comunidad)
async def propose_fusion(request: Request, comunidad: Comunidad, lista_comunidades: List[Comunidad]):
    print("Recibida solicitud de propuesta de fusión")
    global comunidad_fusionable
    global comunidad_principal 
    comunidad_principal = comunidad
    for c in lista_comunidades:

        establecimientos1 = {e.nombre for e in comunidad.establecimientos}
        establecimientos2 = {e.nombre for e in c.establecimientos}
        match_establecimientos = len(establecimientos1 & establecimientos2) / len(establecimientos1 | establecimientos2) >= 0.75


        servicios1 = {s.nombre for s in comunidad.servicios}
        servicios2 = {s.nombre for s in c.servicios}
        match_servicios = len(servicios1 & servicios2) / len(servicios1 | servicios2) >= 0.75

  
        miembros1 = {m.nombre for m in comunidad.miembros}
        miembros2 = {m.nombre for m in c.miembros}
        match_miembros = len(miembros1 & miembros2) / len(miembros1 | miembros2) >= 0.05


        match_confianza = comunidad.gradoDeConfianza == c.gradoDeConfianza 
        if match_confianza and match_miembros and match_establecimientos and match_servicios and c.nombre != comunidad.nombre :
            comunidad_fusionable = c 
            return c 

    raise HTTPException(status_code=400, detail="No se encontró una comunidad adecuada para la fusión")

@app.post("/fuse_communities/", response_model=Comunidad)
async def fuse_communities():
    global comunidad_principal
    global comunidad_fusionable
    if comunidad_principal.nombre == comunidad_fusionable.nombre:
        raise HTTPException(status_code=400, detail="No se pueden fusionar dos comunidades idénticas")

    nuevos_miembros = comunidad_principal.miembros + [m for m in comunidad_fusionable.miembros if m not in comunidad_principal.miembros]

    nuevos_establecimientos = comunidad_principal.establecimientos + [e for e in comunidad_fusionable.establecimientos if e not in comunidad_principal.establecimientos]

    nuevos_servicios = comunidad_principal.servicios + [s for s in comunidad_fusionable.servicios if s not in comunidad_principal.servicios]


    nueva_comunidad = Comunidad(
        nombre=f"{comunidad_principal.nombre}_{comunidad_fusionable.nombre}", 
        miembros=nuevos_miembros,
        establecimientos=nuevos_establecimientos,
        servicios=nuevos_servicios,
        gradoDeConfianza=comunidad_principal.gradoDeConfianza
    )

    return nueva_comunidad

@app.get("/comunidades/fusionables/")
async def obtener_resultado_fusionables(request: Request):
    print("Recibida solicitud para comunidades fusionables")
    global comunidad_fusionable  
    if comunidad_fusionable is not None:  # Revisar si hay una comunidad para fusionar
        return {"comunidad_fusionable": comunidad_fusionable}
    else:
        raise HTTPException(status_code=404, detail="No hay una comunidad sugerida para fusionar")







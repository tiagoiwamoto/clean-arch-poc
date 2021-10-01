package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:24
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.ViacepGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.entity.User;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCreateUsecase {

    private final UserRepository userRepository;
    private final ViacepGateway viacepGateway;

    public ResponseDto prepareToCreateUser(UserDto userDto){

        ResponseEntity<ViacepDto> viacepResponse = this.viacepGateway.call(userDto.getCep().trim()
                .replaceAll("[-.,]", ""));
        //TODO: Validar se o cep retornou um endereco valido
        User userToCreate = new User();
        BeanUtils.copyProperties(userDto, userToCreate);
        userToCreate.setAddress(viacepResponse.getBody().getLogradouro());
        userToCreate.setNeighborhood(viacepResponse.getBody().getBairro());
        userToCreate.setCity(viacepResponse.getBody().getLocalidade());
        userToCreate.setState(viacepResponse.getBody().getUf());
        userToCreate.setCep(viacepResponse.getBody().getCep());

        userToCreate = this.userRepository.save(userToCreate);
        BeanUtils.copyProperties(userToCreate, userDto);

        return ApiResponseDto.of(HttpStatus.CREATED.name(), userDto, "Operação realizada com sucesso");

    }
}

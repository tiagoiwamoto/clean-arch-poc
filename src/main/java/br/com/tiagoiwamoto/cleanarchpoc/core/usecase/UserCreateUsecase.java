package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:24
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.entity.User;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UserCreateUsecase {

    private final UserRepository userRepository;
    private final ViacepUsecase viacepUsecase;

    public ResponseDto prepareToCreateUser(UserDto userDto){
        String cep = userDto.getCep().trim().replaceAll("[-.,]", "");
        log.info("cep to found on Viacep -> {}", cep);
        ViacepDto viacepDto = this.viacepUsecase.connectToViacep(cep);
        log.info("Address recovery from viacep -> {}", viacepDto.toString());
        UserDto createdUserDto = UserDto.buildUserDtoFromUser(buildUserFromViacep(viacepDto, userDto));
        log.info("User is saved, and converted to DTO -> {}", createdUserDto);
        log.info("Operation of create a new user is completed with success");

        return ApiResponseDto.of(HttpStatus.CREATED.name(), createdUserDto, "Operação realizada com sucesso");

    }

    private User buildUserFromViacep(ViacepDto viacepDto, UserDto userDto){
        User userToCreate = User.build();
        userToCreate.setName(userDto.getName());
        userToCreate.setAddress(viacepDto.getLogradouro());
        userToCreate.setNeighborhood(viacepDto.getBairro());
        userToCreate.setCity(viacepDto.getLocalidade());
        userToCreate.setState(viacepDto.getUf());
        userToCreate.setCep(viacepDto.getCep());
        log.info("user created to save on database -> {}", userToCreate);

        return this.userRepository.save(userToCreate);
    }
}

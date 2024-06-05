package pl.jablonskanycz.bakery.clients;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;

public class FileBasedClientRepository implements ClientRepository {
    private static final Path clientPath = Path.of("src", "main", "resources", "CLIENT.csv");
    private AddressRepository addressRepository = new FileBasedAddressRepository();
    @Override
    public List<Client> getAll() {
            List<Client> clients = null;
            try {
                clients = Files.lines(clientPath)
                .skip(1)
                .map(line -> {
                    String[] client = line.split(",");
                    return new Client(
                        Integer.parseInt(client[0]),
                        client[1],
                        client[2],
                        addressRepository.findById(Integer.parseInt(client[3])));
                })
                .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return clients;
    }

    @Override
    public Client findBySurname(String surname) {
        Client client = null;
        try {
            return Files.lines(clientPath)
                    .skip(1)
                    .map(line -> {
                        String[] strings = line.split(",");
                        return new Client(
                                Integer.parseInt(strings[0]),
                                strings[1],
                                strings[2],
                                addressRepository.findById(Integer.parseInt(strings[3])));
                    })
                    .filter(a -> surname.equals(a.getSurname()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Client list is empty"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void addClient(Client clientToAdd) {
        List<String> clientsList = null;
        try {
            clientsList = Files.lines(clientPath)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lastId = clientsList.get(clientsList.size() - 1).split(",")[0];
        int newId = Integer.parseInt(lastId) + 1;
        String line = "\n" + newId + "," + clientToAdd.getName() + "," + clientToAdd.getSurname() + "," + clientToAdd.getAddress();
        try {
            Files.writeString(clientPath, line, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client clientWithOldData, Client clientWithNewData) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(clientPath.toString()));
            List<String> newFileContent = reader.lines()
                    .map(line -> {
                        if (line.startsWith(String.valueOf(clientWithOldData.getId()))) {
                            return clientWithNewData.getId() + "," + clientWithNewData.getName() + "," + clientWithNewData.getSurname() + "," + clientWithNewData.getAddress().getId();
                        } else {
                            return line;
                        }
                    })
                    .collect(Collectors.toList());
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientPath.toString()));
            for (int i = 0; i < newFileContent.size(); i++) {
                if (i == 0) {
                    writer.write(newFileContent.get(i));
                    writer.newLine();
                } else {
                    writer.append(newFileContent.get(i));
                    writer.newLine();
                }
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteClient(Client clientToRemove) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(clientPath.toString()));
            List<String> newFileContent = reader.lines().collect(Collectors.toList());
            for (int i = 0; i < newFileContent.size(); i++) {
                if (newFileContent.get(i).startsWith(String.valueOf(clientToRemove.getId()))) {
                    newFileContent.remove(newFileContent.get(i));
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientPath.toString()));
            for (int i = 0; i < newFileContent.size(); i++) {
                if (i == 0) {
                    writer.write(newFileContent.get(i));
                    writer.newLine();
                } else {
                    writer.append(newFileContent.get(i));
                    writer.newLine();
                }
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




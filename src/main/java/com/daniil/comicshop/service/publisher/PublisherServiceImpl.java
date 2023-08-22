package com.daniil.comicshop.service.publisher;

import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.service.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Publisher add(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Optional<Publisher> getById(int id) {
        return publisherRepository.findById(id);
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher change(Publisher publisher) {
        Optional<Publisher> oldPublisher = publisherRepository.findById(publisher.getId());
        if (oldPublisher.isPresent()){
            oldPublisher.get().getComics().removeAll(publisher.getComics());
            oldPublisher.get().getComics().forEach(publisher::removeComic);
        return publisherRepository.save(publisher);}
        throw new NoSuchElementException();
    }

    @Override
    public Optional<Publisher> delete(int id) {
        Optional<Publisher> publisher = getById(id);
        publisherRepository.deleteById(id);
        return publisher;
    }
}


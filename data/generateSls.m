function generateSls(style, name)
filename = fullfile(style, name);

% load onsets file
onsets = load(strcat(filename, '.onsets'));

% load notes file
notes_file = fopen(fullfile('notes', strcat(name, '.txt')));
if(notes_file == -1)
    disp(fullfile('notes', strcat(name, '.txt')));
end
notes = fgetl(notes_file);
while (~feof(notes_file))
    notes = [notes; {fgetl(notes_file)}];
end
fclose(notes_file);

[wav, fs] = wavread(strcat(filename, '.wav'));
onsets = [onsets;length(wav)];
% convert sample to second
onsets = onsets./fs;
% notes(1) = {'Ssil'};
% notes = [notes];

output_dir = fullfile('sl', style);

if(~exist(output_dir, 'dir'))
    mkdir(output_dir);
end

output_file = fopen(fullfile(output_dir, strcat(strrep(regexprep(name,'[^\w'']',''), '''', ''), '.sl')), 'w');
fprintf(output_file, '#\n');
if(isempty(onsets))
    fprintf(name);
end

for i = 1 : length(onsets)
    if(i == 1)
        notes{i} = 'ssil';
    elseif(i == length(onsets) || strcmp(notes{i}, 'PAU'))
        notes{i} = 'pau';
    else
        notes{i} = strrep(notes{i}, '#', 's');
        notes{i} = strcat(notes{i}, 'N');
    end
   
    for j = 1 : 3    
        if(i == 1)
            fprintf(output_file, '%f 125 %d %s\n', j*onsets(i)/3, j, notes{i});
        else
            fprintf(output_file, '%f 125 %d %s\n', onsets(i-1) + j*(onsets(i)-onsets(i-1))/3, j, notes{i});
        end
    end
end
fclose(output_file);